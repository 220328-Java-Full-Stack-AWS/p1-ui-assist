package revagenda.models;

public enum Type {

LODGING{
    @Override
    public String toString() {
        return "lodging";
    }
},

FOOD{
    @Override
    public String toString() {
        return "food";
    }
},

TRAVEL{
    @Override
    public String toString() {
        return "travel";
    }
},

SOMETHINGELSE{
    @Override
    public String toString(){return "something else";}
}









}
