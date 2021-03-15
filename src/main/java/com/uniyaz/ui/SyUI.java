package com.uniyaz.ui;

import com.uniyaz.core.utils.HibernateUtil;
import com.uniyaz.ui.component.ContentComponent;
import com.uniyaz.ui.component.SyMenuBar;
import com.uniyaz.ui.component.SearchComponent;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

@Theme("mytheme")
@Widgetset("com.uniyaz.MyAppWidgetset")
public class SyUI extends UI {

    private VerticalLayout mainLayout;
    private ContentComponent contentComponent;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        buildMainLayout();
        setContent(mainLayout);
    }

    private void buildMainLayout() {

        mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

        contentComponent = new ContentComponent();

        SearchComponent searchComponent = new SearchComponent();
        mainLayout.addComponent(searchComponent);

        SyMenuBar syMenuBar = new SyMenuBar();

        mainLayout.addComponent(syMenuBar);
        mainLayout.addComponent(contentComponent);

        mainLayout.setExpandRatio(searchComponent, 1f);
        mainLayout.setExpandRatio(syMenuBar, 0.4f);
        mainLayout.setExpandRatio(contentComponent, 8.6f);
    }

    public ContentComponent getContentComponent() {
        return contentComponent;
    }

    public void setContentComponent(ContentComponent contentComponent) {
        this.contentComponent = contentComponent;
    }
}