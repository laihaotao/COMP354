/*
 * description:  Displays info about an ability
 * author(s):    frede
 * reviewer(s):
 * date:         2017-05-29
 */

package ui.selections;

import card.Card;
import game.GameBoard;
import game.Player;
import game.TargetSelector;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

import java.util.HashMap;
import java.util.Optional;

public class TargetSelectorUI extends TargetSelector{

    
    
    
    public static Card selectCard(List<Card> cards){
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle("Target Selector");
        alert.setHeaderText("Please chose the target card");
        alert.setContentText("Options: ");

        HashMap<ButtonType, Card> possibleResults = new HashMap<>();

        alert.getButtonTypes().clear();
        
           cards.forEach((card) -> {
               ButtonType buttonType = new ButtonType(card.getCardName());
               alert.getButtonTypes().add(buttonType);
               possibleResults.put(buttonType, card);
           });

        alert.getButtonTypes().add(new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            return possibleResults.get(result.get());
        }

        return null;
    }

    @Override
    public Card choseOpponentCard(GameBoard gameBoard, Player callingPlayer) {
        Player opponent = gameBoard.getOtherPlayer(callingPlayer);

        List<Card> choices = new ArrayList<>();
        if(opponent.getActivePokemon() != null) {
            choices.add(opponent.getActivePokemon());
        }
        opponent.getBench().forEach(card->{
            choices.add(card);
        });

        return TargetSelectorUI.selectCard(choices);
    }

    @Override
    public Card choseOpponentBench(GameBoard gameBoard, Player callingPlayer) {
        Player opponent = gameBoard.getOtherPlayer(callingPlayer);

        return TargetSelectorUI.selectCard(opponent.getBench());
    }

    @Override
    public Card choseYourCard(GameBoard gameBoard, Player callingPlayer) {
        List<Card> choices = new ArrayList<>();
        if(callingPlayer.getActivePokemon() != null) {
            choices.add(callingPlayer.getActivePokemon());
        }
        callingPlayer.getBench().forEach(card->{
            choices.add(card);
        });

        return TargetSelectorUI.selectCard(choices);
    }

    @Override
    public Card choseYourBench(GameBoard gameBoard, Player callingPlayer) {
        return TargetSelectorUI.selectCard(callingPlayer.getBench());
    }
}
