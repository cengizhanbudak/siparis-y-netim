package com.uniyaz.ui.page;

import com.uniyaz.core.domain.Musteri;
import com.uniyaz.core.service.MusteriService;
import com.uniyaz.ui.component.SySaveButton;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;

public class MusteriPage extends VerticalLayout {


    @PropertyId("id")
    private TextField id;

    @PropertyId("adi")
    private TextField adi;

    @PropertyId("bakiye")
    private TextField bakiye;

    @PropertyId("adres")
    private TextField adres;


    private FormLayout mainLayout;

    private BeanItem<Musteri> musteriBeanItem;
    private FieldGroup binder;
    private SySaveButton sySaveButton;


    public MusteriPage() {
        this(new Musteri());

    }

    public MusteriPage(Musteri musteri) {
        setSizeFull();
        buildMainLayout();
        addComponent(mainLayout);
        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

        musteriBeanItem = new BeanItem<Musteri>(musteri);
        binder = new FieldGroup(musteriBeanItem);
        binder.bindMemberFields(this);

    }

    private void buildMainLayout() {
        mainLayout = new FormLayout();
        mainLayout.setSizeUndefined();

        id = new TextField();
        id.setCaption("ID");
        id.setEnabled(false);
        mainLayout.addComponent(id);

        adi = new TextField();
        adi.setCaption("Adı");
        mainLayout.addComponent(adi);

        bakiye = new TextField();
        bakiye.setCaption("Bakiye");
        mainLayout.addComponent(bakiye);

        adres = new TextField();
        adres.setCaption("Adres");
        mainLayout.addComponent(adres);

        sySaveButton = new SySaveButton();
        sySaveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    binder.commit();
                    Musteri musteri=musteriBeanItem.getBean();
                    MusteriService musteriService=new MusteriService();
                    musteriService.saveMusteri(musteri);

                } catch (FieldGroup.CommitException e) {
                    e.printStackTrace();
                }
            }
        });
        mainLayout.addComponent(sySaveButton);
    }

    public void musteriDelete(Musteri musteri){

        try {
            binder.commit();
            MusteriService musteriService=new MusteriService();
            musteriService.deleteMusteri(musteri);

        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }

    }

}



