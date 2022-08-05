package com.flexwareinnovation.web;

import com.flexwareinnovation.GatewayHook;
import com.flexwareinnovation.records.PersonRecord;
import com.inductiveautomation.ignition.gateway.localdb.persistence.RecordMeta;
import com.inductiveautomation.ignition.gateway.web.components.RecordActionTable;
import com.inductiveautomation.ignition.gateway.web.models.DefaultConfigTab;
import com.inductiveautomation.ignition.gateway.web.models.IConfigTab;
import com.inductiveautomation.ignition.gateway.web.pages.IConfigPage;
import org.apache.commons.lang3.tuple.Pair;



public class SettingsPage extends RecordActionTable<PersonRecord> {
    public static final Pair<String, String> MENU_LOCATION = Pair.of(GatewayHook.CONFIG_CATEGORY.getName(), "nickmodule");

    public static final IConfigTab MENU_ENTRY = DefaultConfigTab.builder()
            .category(GatewayHook.CONFIG_CATEGORY)
            .name("nickmodule") // this must be the same as the 'right' param in MENU_LOCATION
            .i18n("NicksModule.nav.settings.title") // I'm not sure where this is used
            .page(SettingsPage.class)
            .terms("NicksModule", "cool", "people") // I don't understand what this does exactly
            .build();

    public SettingsPage(final IConfigPage configPage) {
        super(configPage);

    }
    @Override
    protected RecordMeta<PersonRecord> getRecordMeta() {
        return PersonRecord.META;
    }


    @Override
    public Pair<String, String> getMenuLocation() {return MENU_ENTRY.getMenuLocation();}

    @Override
    protected String getTitleKey() {
        return "nav.settings.title";
    } // I'm not certain that this reference works and am not sure where it's used
}
