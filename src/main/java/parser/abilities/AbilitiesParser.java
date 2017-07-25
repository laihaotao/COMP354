package parser.abilities;

import entry.Config;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.commons.*;
import parser.tokenizer.*;
import parser.ui.AbilityTemplatePane;
import parser.ui.TokenPane;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frede on 2017-05-23.
 * <p>
 * Parses abilities from text file using a LanguageTokenizer
 */
public class AbilitiesParser {

    static Logger log = LogManager.getLogger(AbilitiesParser.class.getName());

    LanguageTokenizer tokenizer;

    /**
     * @param fileName File name that needs to be parsed
     */
    public AbilitiesParser(String fileName) {
        tokenizer = new LanguageTokenizer(fileName);
    }

    /**
     * Parse a file into a series of abilities
     *
     * @return List of all abilities parsed
     */
    public AbilityTemplate[] parse() {
        //Get a list of all tokens in the file using the tokenizer
        List<TokenScope> scopes = tokenizer.tokenize();

        //Where the abilities are going to be stored
        List<AbilityTemplate> templates = new ArrayList<>();

        //For each "Scope" token, process it as a null / parent scope
        int i = 0;
        scopes.forEach((scope) -> {
            templates.add(parseNullScope(scope));
        });

        if (Config.DEBUG) {
            Stage stage = new Stage();
            stage.setTitle("Parser");

            BorderPane mainView = new BorderPane();

            TreeItem tokenItemPane = new TokenPane(scopes.toArray(new Token[0]));

            TreeView tokenView = new TreeView<>(tokenItemPane);
            tokenView.setMinWidth(600);
            mainView.setLeft(tokenView);

            TreeItem abilityItemPane = new AbilityTemplatePane((templates.toArray(new
                    AbilityTemplate[0])));
            TreeView abilityView = new TreeView(abilityItemPane);
            abilityView.setMinWidth(600);
            mainView.setRight(abilityView);

            stage.setScene(new Scene(mainView, 1200, 800));
            stage.show();
        }


        return templates.toArray(new AbilityTemplate[0]);
    }

    /**
     * Parse a parent scope
     *
     * @param scope scope to parse
     * @return
     */
    private AbilityTemplate parseNullScope(TokenScope scope) {

        //get a token stream for this scope
        TokenStream tokenStream = new TokenStream(scope.tokens);

        //First token should always be the name of the ability
        String name = tokenStream.validateTokenString().value;
        if (name == null) {
            log.error("Name token needs to be a string: " + tokenStream.getNextToken());
            return null;
        }

        //blank ability template
        AbilityTemplate template = new AbilityTemplate(name);

        //log.debug(" " + name+ " ");

        //Go through tokens, parsing them into ability parts
        template.parts.addAll(getPartsFromScope(tokenStream));

        return template;
    }
    
    private List<AbilityPart> parseNextPartSafe(TokenStream tokenStream){
        List<AbilityPart> parts;
        TokenScope scope = null;
        if((scope = tokenStream.validateTokenScope()) != null){
            parts = getPartsFromScope(scope);
        }else{
            parts = new ArrayList<>();
            parts.add(parseNextPart(tokenStream));
        }
        return parts;
    }
    private List<AbilityPart> getPartsFromScope(TokenStream tokenStream){
        List<AbilityPart> parts = new ArrayList<>();
        AbilityPart abilityPart = null;
        while ((abilityPart = parseNextPart(tokenStream)) != null) {
            parts.add(abilityPart);
        }

        return parts;
    }
    private List<AbilityPart> getPartsFromScope(TokenScope scope){
        return getPartsFromScope(new TokenStream(scope.tokens));
    }

    private AbilityPart parseNextPart(TokenStream tokenStream) {
        Token token = tokenStream.getNextToken();
        if (token == null) {
            return null;
        }

        if (token instanceof TokenString) {
            TokenString tokenString = (TokenString) token;
            switch (tokenString.value) {
                case "deck":
                    return parsePartDeck(tokenStream);
                case "dam":
                    return parseDamPart(tokenStream);
                case "draw":
                    return parseDrawPart(tokenStream);
                case "cond":
                    return parseCondPart(tokenStream);
                case "heal":
                    return parseHealPart(tokenStream);
                case "deenergize":
                    return parseDeenergizePart(tokenStream);
                case "reenergize":
                    return parseReenergizePart(tokenStream);
                case "redamage":
                    return parseRedamagePart(tokenStream);
                case "swap":
                    return parseSwapPart(tokenStream);
                case "add":
                    return parseAddPart(tokenStream);
                case "applystat":
                    return parseApplystatPart(tokenStream);
                case "destat":
                    return parseDestatPart(tokenStream);
                case "search":
                    return parseSearchPart(tokenStream);

                default:
                    waitUntil(tokenStream, TokenType.SEPERATOR);
                    return parseNextPart(tokenStream);
            }

        } else if (token instanceof TokenSeparator) {
            return parseNextPart(tokenStream);
        }
        return null;
    }

    /**
     * Go through tokens until we reach the desired one
     *
     * @param stream
     * @param type
     */
    private void waitUntil(TokenStream stream, TokenType type) {
        Token token = null;
        while ((token = stream.getNextToken()) != null) {
            if (token.type == type) {
                break;
            }
        }
    }

    /**
     * Parse the deck ability part
     *
     * @param tokenStream
     * @return
     */
    private AbilityPart parsePartDeck(TokenStream tokenStream) {

        //log.debug("---Deck---");

        TargetProperty targetPart = TargetProperty.read(tokenStream);

        TargetProperty destinationPart = TargetProperty.read(tokenStream);

        TokenString choice = null;
        if (tokenStream.validateTokenString("choice") != null) {
            choice = tokenStream.validateTokenString();
        }

        Token amount = tokenStream.getNextToken();
        if (!(amount instanceof TokenScope) && !(amount instanceof TokenInteger)) {
            tokenStream.backtrack();
        }
        //log.debug(targetPart);
        //log.debug(destinationPart);
        //log.debug("Choice: "+Formatting.toSafeString(choice));
        return new AbilityPartDeck(targetPart, destinationPart, choice, amount);
    }

    /**
     * Parse the Dam (damage) ability part
     *
     * @param tokenStream
     * @return
     */
    private AbilityPart parseDamPart(TokenStream tokenStream) {
        //log.debug("---Dam---");

        TargetProperty targetPart = TargetProperty.read(tokenStream);

        Token amount = tokenStream.getNextToken();

        //log.debug(targetPart);
        //log.debug("Amount: " + amount);

        return new AbilityPartDam(targetPart, amount);
    }

    private AbilityPart parseHealPart(TokenStream tokenStream) {
        TargetProperty target = TargetProperty.read(tokenStream);
        Token amount = tokenStream.getNextToken();

        return new AbilityPartHeal(target, amount);
    }

    private AbilityPart parseDeenergizePart(TokenStream tokenStream) {
        TargetProperty target = TargetProperty.read(tokenStream);
        Token amount = tokenStream.getNextToken();

        return new AbilityPartDeenergize(target, amount);
    }

    private AbilityPart parseReenergizePart(TokenStream tokenStream) {
        TargetProperty source = TargetProperty.read(tokenStream);
        TargetProperty target = TargetProperty.read(tokenStream);
        Token amount = tokenStream.getNextToken();

        return new AbilityPartReenergize(source, target, amount);
    }

    private AbilityPart parseRedamagePart(TokenStream tokenStream) {
        TargetProperty source = TargetProperty.read(tokenStream);
        TargetProperty target = TargetProperty.read(tokenStream);
        Token amount = tokenStream.getNextToken();

        return new AbilityPartRedamage(source, target, amount);
    }

    private AbilityPart parseSwapPart(TokenStream tokenStream) {
        TargetProperty source = TargetProperty.read(tokenStream);
        TargetProperty destination = TargetProperty.read(tokenStream);

        return new AbilityPartSwap(source, destination);
    }

    /**
     * Parse the Draw ability part
     *
     * @param tokenStream
     * @return
     */
    private AbilityPart parseDrawPart(TokenStream tokenStream) {
        TargetProperty target = new TargetProperty(tokenStream.validateTokenString(), null);

        Token amount = tokenStream.getNextToken();
        if (!(amount instanceof TokenScope) && !(amount instanceof TokenInteger)) {
            tokenStream.backtrack();
        }

        return new AbilityPartDraw(target, amount);
    }

    private AbilityPart parseCondPart(TokenStream tokenStream) {
        Token type = tokenStream.getNextToken();

        Condition condition = null;

        if (type instanceof TokenString) {
            switch (((TokenString) type).value) {
                case "healed":
                    TargetProperty targetProperty = TargetProperty.read(tokenStream);
                    //apply if target has been healed
                    break;
                case "flip":
                    condition = new ConditionFlip();
                    break;
                case "ability":
                    // ???
                    break;
                case "choice":
                    //player choses
                    break;
            }
        } else if (type instanceof TokenCondition) {

        }
       
        List<AbilityPart> trueParts = parseNextPartSafe(tokenStream);
        tokenStream.validateTokenString("else");
        List<AbilityPart> falseParts = parseNextPartSafe(tokenStream);
        
        AbilityPartCond abilityPartCond = new AbilityPartCond(condition, trueParts, falseParts);

        return abilityPartCond;

    }

    private AbilityPart parseAddPart(TokenStream tokenStream) {
        TargetProperty target = TargetProperty.read(tokenStream);
        TriggerProperty triggerProperty = TriggerProperty.read(tokenStream);
        AbilityPart abilityToAdd = parseNextPart(new TokenStream(((TokenScope) tokenStream.getNextToken()).tokens));

        return new AbilityPartAdd(target, triggerProperty, abilityToAdd);
    }

    private AbilityPart parseApplystatPart(TokenStream tokenStream) {
        StatusProperty status = StatusProperty.read(tokenStream);
        TargetProperty target = TargetProperty.readUnsafe(tokenStream);

        return new AbilityPartApplystat(status, target);
    }

    private AbilityPart parseDestatPart(TokenStream tokenStream) {
        TargetProperty target = TargetProperty.read(tokenStream);

        return new AbilityPartDestat(target);
    }

    private AbilityPart parseSearchPart(TokenStream tokenStream){

        TargetProperty target = TargetProperty.read(tokenStream);

        TokenString source = null;
        if(tokenStream.validateTokenString("source") != null){
            source = tokenStream.validateTokenString();
        }

        Filter filter = new Filter();
        if(tokenStream.validateTokenString("filter") != null){
            TokenString filterType = null;
            if((filterType = tokenStream.validateTokenString()) != null){
                switch(filterType.value){
                    case "energy": {
                        FilterEnergy energyFilter = new FilterEnergy();
                        if (tokenStream.validateTokenString("cat") != null) {
                            TokenString pokemonCategory = tokenStream.validateTokenString();
                            energyFilter.setCategory(pokemonCategory.value);
                        }

                        filter = energyFilter;
                    }  break;
                    case "cat": {
                        TokenString category = tokenStream.validateTokenString();
                        filter = new FilterCategory(category.value);
                    }break;
                    case "pokemon": {
                        FilterPokemon pokemonFilter = new FilterPokemon();
                        if (tokenStream.validateTokenString("cat") != null) {
                            TokenString pokemonCategory = tokenStream.validateTokenString();
                            pokemonFilter.setCategory(pokemonCategory.value);
                        }
                        filter = pokemonFilter;
                    }break;
                    case "evolves-from": {
                        TargetProperty evolvesFromTarget = TargetProperty.read(tokenStream);
                        filter =  new FilterEvolveFrom(evolvesFromTarget);
                    }break;
                    case "top":
                        break;
                    case "bottom":
                        break;

                }
            }

        }

        Token amount = tokenStream.getNextToken();


        return new AbilityPartSearch(target, source, filter, amount);
    }
}
