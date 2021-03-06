package views.beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import controllers.UseCaseFulController;
import controllers.UseCaseLessController;

@Named
@SessionScoped
public class Ejb implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private UseCaseLessController useCaseLessController;

    @Inject
    private UseCaseFulController useCaseFulController;

    private String value;

    private List<String> items;

    private boolean active = true;

    public String stateless() {
        value += ":" + this.useCaseLessController.echo(value);
        return null;
    }

    public String statefulAdd() {
        useCaseFulController.addItem(value);
        items = useCaseFulController.items();
        return null;
    }

    public String statefulRemove() {
        useCaseFulController.remove();
        active = false;
        return null;
    }

    public void setUseCaseFulController(UseCaseFulController useCaseFulController) {
        this.useCaseFulController = useCaseFulController;
    }

    public void setUseCaseLessController(UseCaseLessController useCaseLessController) {
        this.useCaseLessController = useCaseLessController;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public boolean isActive() {
        return active;
    }

}
