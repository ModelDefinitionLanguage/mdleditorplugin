package eu.ddmore.pharmacometrics.model.trialdesign.structure;

import java.util.ArrayList;
import java.util.List;

public class Structure {

    private List<Arm> arms;
    private List<Epoch> epochs;
    private List<Cell> cells;

    public Structure() {
        arms = new ArrayList<Arm>();
        epochs = new ArrayList<Epoch>();
        cells = new ArrayList<Cell>();
    }
    
    public List<Cell> getCells() {
        return cells;
    }

    public void addCell(Cell c) {
        cells.add(c); 
    }
    
    public List<Cell> getCellsOfArm(Arm arm) {
        List<Cell> cellsOfArm = new ArrayList<Cell>();
        for (Cell cell : cells) {
            if (cell.appearsInArm(arm)) {
                cellsOfArm.add(cell);
            }
        }
        return cellsOfArm;
    }

    public List<Cell> getCellsOfEpoch(Epoch epoch) {
        List<Cell> cellsOfEpoch = new ArrayList<Cell>();
        for (Cell cell : cells) {
            if (cell.getEpoch().equals(epoch)) {
                cellsOfEpoch.add(cell);
            }
        }
        return cellsOfEpoch;
    }

    public void setArm(Arm a) {
        arms.add(a);
    }

    public void setEpochAt(Epoch e, int index) {
        if (index < epochs.size()) {
            epochs.set(index, e);
        } else if (index == epochs.size()) {
            epochs.add(e);
        } else {
            throw new IllegalArgumentException(String.format("Set index can be no more than: %d", epochs.size()));
        }
    }

    public List<Arm> getArms() {
        return arms;
    }

    public List<Epoch> getEpochs() {
        return epochs;
    }

    @Override
    public String toString() {
        return "Structure [arms=" + arms + ", epochs=" + epochs + ", cells=" + cells + "]";
    }

}
