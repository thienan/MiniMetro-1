package model;

import java.util.ArrayList;

/**
 * Created by romainhry on 07/11/2016.
 */
public class Train {
    private int nextPointIndex; // +2 if at a station to know the next station
    private java.util.List<Client> clientList;
    private int numberWagon ;
    private Line line ;
    private boolean direction ;
    

    public Train (int index, Line l, boolean dir) {
        nextPointIndex = index;
        line = l;
        direction = dir;
        clientList = new ArrayList<>();
        numberWagon = 0;
    }

    public boolean isFull() {
    	return clientList.size()==Game.trainCapacity*(numberWagon+1) ;
    }
    
    public void setDirection(boolean dir) {
    	direction = dir;
    }

    public void getNextPointIndex() {

    }

    public Station nextStation() {
        if(direction)
            return line.getStationList().get((nextPointIndex+2)/2);
        return line.getStationList().get((nextPointIndex-2)/2);
    }

    public Station currentStation() {
        return line.getStationList().get(nextPointIndex/2);
    }

    public Line getLine() {
        return line;
    }

    public void addWagon() {
    	
    }

    public void swapWagon(Train added) {

    }

    public void changeLine (Position newPosition,Line newLine) {

    }

    public void setPosition(Position pos) {

    }
    
    public void addClient(Client client) {
        clientList.add(client);
    }

    public void removeClient(Client client) {
        clientList.remove(client);
    }

    public void stopAtStation () {
        /* Makes the clients to get off the train */
        for(int i = 0;i<clientList.size()-1;++i) {
            Client client = clientList.get(i);
            if(client.willGetOff(this))
                --i;
        }
        /* Makes the clients of the current station try to board in */
        Station station = currentStation();
        for(Client cl : station.getClientList())
            cl.tryBoarding(this);

    }

    public void move () {
        if(direction) {
            /* checking for the end of the line and if the line is a loop */
            if(nextPointIndex == line.getPath().size()-1 && line.isLoop())
                nextPointIndex = 0;
            else if(nextPointIndex == line.getPath().size()-1)
                direction = false;
        }
        else {
            if(nextPointIndex == 0 && line.isLoop())
                nextPointIndex = line.getPath().size()-1;
            else
                direction = true;
        }

        /* moving the train index on his line */
        if(direction)
            ++nextPointIndex;
        else
            --nextPointIndex;
    }
    
}
