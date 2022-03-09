package com.flexwareinnovation.web;

import com.flexwareinnovation.GatewayHook;
import com.flexwareinnovation.records.PersonRecord;
import com.inductiveautomation.ignition.gateway.model.IgnitionWebApp;
import com.inductiveautomation.ignition.gateway.web.components.RecordEditForm;
import com.inductiveautomation.ignition.gateway.web.models.LenientResourceModel;
import com.inductiveautomation.ignition.gateway.web.pages.IConfigPage;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.wicket.Application;

/**
 * extends  {@link RecordEditForm} to provide a page where we can edit records in our PersistentRecord.
 * */
public class SettingsPage extends RecordEditForm {
    public static final Pair<String, String> MENU_LOCATION = Pair.of(GatewayHook.CONFIG_CATEGORY.getName(), "NicksModule");

    public SettingsPage(final IConfigPage configPage) {
        super(configPage, null, new LenientResourceModel("NicksModule.nav.settings.panelTitle"),
                ((IgnitionWebApp) Application.get()).getContext().getPersistenceInterface().find(PersonRecord.META, 0L)
                );

    }

    @Override
    public Pair<String, String> getMenuLocation() {return MENU_LOCATION;}

}
