package javafxTest;

import javafx.scene.shape.Shape;
import model.Client;
import model.Position;
import model.Station;

import static javafxTest.defaultShapes.*;

public class fxClient {
    Shape shape;

    public fxClient(Client c) {
        switch(c.getType()) {
            case CIRCLE: shape = getLittleCircle(); break;
            case SQUARE: shape = getLittleSquare(); break;
            case TRIANGLE: shape = getLittleTriangle() ; break;
            case STAR: shape = getLittleStar(); break;
            case CROSS: shape = getLittleCross(); break;
            default: shape = null;
        }

        Station st = c.getStation();
        int index = st.getClientList().size();
        Position p = new Position(st.getPosition().getX()+10+index*20,st.getPosition().getY()-10);
        defaultShapes.setPosition(shape,p);

    }
}
