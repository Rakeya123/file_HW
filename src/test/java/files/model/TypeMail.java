package files.model;


import java.util.List;

public class TypeMail {
    private String taskType;
    private String route;
    private int weight;
    private List<String> dimensions;
    private boolean printDocument;

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    public List<String> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<String> dimensions) {
        this.dimensions = dimensions;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }


    public boolean isPrintDocument() {
        return printDocument;
    }

    public void setPrintDocument(boolean printDocument) {
        this.printDocument = printDocument;
    }
}
