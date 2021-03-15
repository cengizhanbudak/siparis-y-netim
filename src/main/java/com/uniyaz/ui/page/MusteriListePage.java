package com.uniyaz.ui.page;

import com.uniyaz.core.domain.Musteri;
import com.uniyaz.core.service.MusteriService;
import com.uniyaz.ui.SyUI;
import com.uniyaz.ui.component.ContentComponent;
import com.uniyaz.ui.component.SyDeleteButton;
import com.uniyaz.ui.component.SyEditButton;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class MusteriListePage extends VerticalLayout {

    private VerticalLayout mainLayout;
    private Table table;
    private Container container;

    public MusteriListePage() {

        setSizeFull();
        buildMainLayout();
        addComponent(mainLayout);

        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

        fillTable();
    }

    private void buildMainLayout() {


        mainLayout = new VerticalLayout();
        mainLayout.setSizeUndefined();

        buildTable();
        mainLayout.addComponent(table);
    }

    private void buildTable() {


        table = new Table();

        buildContainer();
        table.setContainerDataSource(container);
        table.setColumnHeaders("ID", "ADI", "BAKİYE", "ADRES", "","");
    }

    private void buildContainer() {

        container = new IndexedContainer();
        container.addContainerProperty("id", Long.class, null);
        container.addContainerProperty("adi", String.class, null);
        container.addContainerProperty("bakiye", Integer.class, null);
        container.addContainerProperty("adres", String.class, null);
        container.addContainerProperty("guncelle", SyEditButton.class, null);
        container.addContainerProperty("sil",Button.class,null);

    }

    private void fillTable() {

        MusteriService musteriService = new MusteriService();
        List<Musteri> musteriList = musteriService.findAllHql();
        for (Musteri musteri : musteriList) {
            Item item = container.addItem(musteri);
            item.getItemProperty("id").setValue(musteri.getId());
            item.getItemProperty("adi").setValue(musteri.getAdi());
            item.getItemProperty("bakiye").setValue(musteri.getBakiye());
            item.getItemProperty("adres").setValue(musteri.getAdres());

            SyEditButton guncelle = new SyEditButton();
            guncelle.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {

                    SyUI syUI = (SyUI) SyUI.getCurrent();
                    ContentComponent contentComponent = syUI.getContentComponent();

                    MusteriPage musteriPage = new MusteriPage(musteri);
                    contentComponent.addComponent(musteriPage);
                }
            });
            item.getItemProperty("guncelle").setValue(guncelle);
            
            SyDeleteButton sil=new SyDeleteButton();
            sil.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {

                    MusteriPage musteriPage = new MusteriPage(musteri);
                    musteriPage.musteriDelete(musteri);


                }
            });
            item.getItemProperty("sil").setValue(sil);
        }


    }
}
