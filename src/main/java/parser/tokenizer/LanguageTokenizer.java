package parser.tokenizer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import util.ResourceReader;

/**
 * Parses a file for the language the teacher defined in the text files
 */
public class LanguageTokenizer {

    /**
     * File name to load text from
     */
    private String fileName;

    public LanguageTokenizer(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Parse and print output of file
     */
    public List<TokenScope> tokenize() {
        //get the path to the filename. This assumes the file is in resources
        InputStream is = ResourceReader.readFile(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //Each line is a "Scope". This means TokenScope is always the parent token
        List<TokenScope> lineScopes = new ArrayList<>();

        //read each line in the file and add it to lineScopes, which will be returned
        try {
//      Files.lines(file.toPath()).forEach((line) -> {
//        lineScopes.add(parseLine(line));
//      });
            String line;
            while ((line = br.readLine()) != null) {
                lineScopes.add(parseLine(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineScopes;
    }

    /**
     * Will tokenize a file line, each line is isolated
     *
     * @param line
     */
    private TokenScope parseLine(String line) {

        //System.out.println("Parsing: " + line);
        TokenScope scope = processScope(line, 0, '\n');
        //printScope(scope, 0);

        return scope;

    }

    /**
     * Print out the parsed lines recursively
     *
     * @param scope
     * @param depth
     */
    private void printScope(TokenScope scope, int depth) {

        String tabs = "";
        for (int i = 0; i < depth; i++) {
            tabs += "\t";
        }

        System.out.println(tabs + "Prefix: " + scope.prefix);
        tabs += "\t";

        for (Token token : scope.tokens) {
            if (token instanceof TokenScope) {
                printScope((TokenScope) token, depth + 1);
            } else {
                System.out.println(tabs + token);
            }
        }
    }

    /**
     * Creates a token from a string. Creates integer if possible and otherwise creates a string
     * token.
     *
     * @param location
     * @param s
     * @return
     */
    private Token createTokenFromString(int location, String s) {
        try {
            int value = Integer.parseInt(s);
            return new TokenInteger(location, value);
        } catch (Exception e) {
            return new TokenString(location, s);
        }
    }

    /**
     * Parse / process a scope, will create scopes recursively
     *
     * @param line  Current line that is getting parsed
     * @param start Where this scope starts
     * @return
     */
    private TokenScope processScope(String line, int start, char endChar) {
        //contains all the tokens in this scope
        List<Token> tokens = new ArrayList<>();

        //Used to build token by adding characters as we scam them
        String currentTokenString = "";

        //Remebers if an arithmetic operation was present
        //TODO This needs to be changed if we need to implement precedence of operations, ie
      // Multiplication having higher priority than addition
        OperatorType lastArithmeticTokenType = null;

        //scan through every character from the scope start
        for (int location = start; location < line.length(); location++) {
            char c = line.charAt(location);

            switch (c) {
                //Comment
                case '#':
                    TokenScope scope1 = new TokenScope(location);
                    scope1.tokens = tokens;
                    return scope1;

                //separator for ability parts
                case ',':
                    addToken(tokens, createTokenFromString(location, currentTokenString),
                            lastArithmeticTokenType);
                    lastArithmeticTokenType = null;
                    currentTokenString = "";
                    tokens.add(new TokenSeparator(location));
                    break;

                //separates ability parameters
                case ':':
                    addToken(tokens, createTokenFromString(location, currentTokenString),
                            lastArithmeticTokenType);
                    lastArithmeticTokenType = null;
                    currentTokenString = "";
                    break;

                //start of scope
                case '(':
                    TokenScope tScope = processScope(line, location + 1, ')');
                    addToken(tokens, tScope, lastArithmeticTokenType);
                    lastArithmeticTokenType = null;
                    location = tScope.endLocation;
                    tScope.prefix = currentTokenString;
                    currentTokenString = "";
                    break;
                //alternate scope start
                case '[':
                    TokenScope tScope2 = processScope(line, location + 1, ']');
                    addToken(tokens, tScope2, lastArithmeticTokenType);
                    lastArithmeticTokenType = null;
                    location = tScope2.endLocation;
                    tScope2.prefix = currentTokenString;
                    currentTokenString = "";
                    ;
                    break;

                //multiplication operator
                case '*':
                    //create a new token if necessary
                    if (currentTokenString.length() > 0)
                        addToken(tokens, createTokenFromString(location, currentTokenString),
                                lastArithmeticTokenType);

                    //remember that the operator was there
                    lastArithmeticTokenType = OperatorType.MULTIPLICATION;
                    currentTokenString = "";
                    break;
                case '>':
                    //create a new token if necessary
                    if (currentTokenString.length() > 0)
                        addToken(tokens, createTokenFromString(location, currentTokenString),
                            lastArithmeticTokenType);

                    //remember that the operator was there
                    lastArithmeticTokenType = OperatorType.GREATER;
                    currentTokenString = "";
                    break;
                default:
                    //If character signals end of scope, add last tokena nd then return the scope
                    if (endChar != '\n')
                        if (c == endChar) {
                            addToken(tokens, createTokenFromString(location, currentTokenString),
                                    lastArithmeticTokenType);
                            lastArithmeticTokenType = null;
                            TokenScope scope = new TokenScope(location);
                            scope.tokens = tokens;
                            return scope;
                        }

                    currentTokenString += c;
            }
        }

        //If the program gets here, this means the line is at the end, which end the scope

        //add last token
        addToken(tokens, createTokenFromString(line.length(), currentTokenString),
                lastArithmeticTokenType);
        lastArithmeticTokenType = null;

        //create a return scope
        TokenScope scope = new TokenScope(line.length());
        scope.tokens = tokens;

        return scope;
    }

    /**
     * This checks if the last two tokens need to be replaced by an Arithmetic token
     *
     * @param lastOperatorTokenType
     * @param tokens
     */
    private void checkOperator(OperatorType lastOperatorTokenType, List<Token> tokens) {
        if (lastOperatorTokenType != null && lastOperatorTokenType != OperatorType.NULL) {

            //get and remove last two tokens
            Token left = tokens.get(tokens.size() - 2);
            Token right = tokens.get(tokens.size() - 1);
            tokens.remove(tokens.size() - 1);
            tokens.remove(tokens.size() - 1);

            OperatorType type = lastOperatorTokenType;
            lastOperatorTokenType = null;
            
            Token newToken = null;
            switch(type){
                case MULTIPLICATION:
                    newToken = new TokenArithmetic(right.endLocation, type, left, right);
                    break;
                case GREATER:
                    newToken = new TokenCondition(right.endLocation, type, left, right);
            }
            
            addToken(tokens, newToken, lastOperatorTokenType);
        }
    }

    /**
     * Add token and then check for arithmetic operations
     *
     * @param tokens         Where to add the token
     * @param token          Token to add
     * @param operatorType arithmetic type to check
     */
    private void addToken(List<Token> tokens, Token token, OperatorType operatorType) {
        tokens.add(token);
        checkOperator(operatorType, tokens);
    }
}
