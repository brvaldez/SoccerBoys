package View;

import Model.Athlete;

public class PlaceHolderAthlete extends Athlete {

    public PlaceHolderAthlete() {
        super("[Select an athlete]", null,null, null, null, 0); // Placeholder name
    }

    @Override
    public String toString() {
        return getName(); // Return placeholder text for display
    }
}
