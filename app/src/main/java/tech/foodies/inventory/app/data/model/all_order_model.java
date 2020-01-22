package tech.foodies.inventory.app.data.model;

public class all_order_model {

    String sname;
    String name;
    String unique_id;

     public all_order_model(String sname, String name,String unique_id) {
        this.sname = sname;
        this.name = name;
        this.unique_id = unique_id;
    }

    public String getSname(){return sname;}

    public void setSname(String sname){this.sname = sname;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

}
