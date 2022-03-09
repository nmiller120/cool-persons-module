package com.flexwareinnovation;

import com.flexwareinnovation.records.PersonRecord;
import com.flexwareinnovation.web.SettingsPage;
import com.inductiveautomation.ignition.common.BundleUtil;
import com.inductiveautomation.ignition.common.licensing.LicenseState;
import com.inductiveautomation.ignition.gateway.localdb.persistence.IRecordListener;
import com.inductiveautomation.ignition.gateway.model.AbstractGatewayModuleHook;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;
import com.inductiveautomation.ignition.common.util.LogUtil;
import com.inductiveautomation.ignition.common.util.LoggerEx;
import com.inductiveautomation.ignition.gateway.web.components.ConfigPanel;
import com.inductiveautomation.ignition.gateway.web.models.ConfigCategory;
import com.inductiveautomation.ignition.gateway.web.models.DefaultConfigTab;
import com.inductiveautomation.ignition.gateway.web.models.IConfigTab;
import com.inductiveautomation.ignition.gateway.web.models.KeyValue;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class GatewayHook extends AbstractGatewayModuleHook {

    // logger
    private final LoggerEx logger = LogUtil.getLogger("NicksModuleHook");

    // gateway context
    private GatewayContext context;


    // -- Setup / Startup / Shutdown overrides ----------------------------------
    @Override
    public void setup(GatewayContext gatewayContext) {
        this.context = gatewayContext;

        logger.info("Beginning setup of my custom module.");

        // Register GatewayHook.properties by registering the GatewayHook.class with BundleUtils
        BundleUtil.get().addBundle("NicksModule", getClass(), "NicksModule");

        // Verify tables for persistent records if necessary
        verifySchema(context);

        // create records if needed
        maybeCreatePersonRecord(context, 0L, "Nick",
                "Miller", true);

        // get the settings records and do something with it...
        PersonRecord theOneRecord = context.getLocalPersistenceInterface().find(PersonRecord.META, 0L);
        logger.info("Firstname: " + theOneRecord.getFirstname());
        logger.info("Lastname: " + theOneRecord.getLastname());

        // listen for updates to the settings record...
        PersonRecord.META.addRecordListener(
                new IRecordListener<PersonRecord>() {
                    @Override
                    public void recordUpdated(PersonRecord personRecord) {
                        logger.info("recordUpdated()");
                    }

                    @Override
                    public void recordAdded(PersonRecord personRecord) {
                        logger.info("recordAdded()");
                    }

                    @Override
                    public void recordDeleted(KeyValue keyValue) {
                        logger.info("recordDeleted()");
                    }
                });
        logger.debug("Setup complete.");


    }

    @Override
    public void startup(LicenseState licenseState) {}

    @Override
    public void shutdown() {
        /* remove our bundle */
        BundleUtil.get().removeBundle("NicksModule");
    }

    // -- Config panel override ----------------------------------------------

    /**
     * This sets up the config panel
     */
    public static final ConfigCategory CONFIG_CATEGORY =
            new ConfigCategory("PersonsConfiguration", "NicksModule.nav.header", 700);


    /**
     * An IConfigTab contains all the info necessary to create a link to your config page on the gateway nav menu.
     * In order to make sure the breadcrumb and navigation works properly, the 'name' field should line up
     * with the right-hand value returned from {@link ConfigPanel#getMenuLocation}. In this case name("homeconnect")
     * lines up with HCSettingsPage#getMenuLocation().getRight()
     */
    public static final IConfigTab MY_CONFIG_ENTRY = DefaultConfigTab.builder()
            .category(CONFIG_CATEGORY)
            .name("NicksModule")
            .i18n("NicksModule.nav.settings.title")
            .page(SettingsPage.class)
            .terms("home connect settings")
            .build();

    @Override
    public List<? extends IConfigTab> getConfigPanels() {
        return Collections.singletonList(
                MY_CONFIG_ENTRY
        );
    }

    @Override
    public List<ConfigCategory> getConfigCategories() {
        return Collections.singletonList(CONFIG_CATEGORY);
    }

    // -- Custom methods -----------------------------------------------------
    private void verifySchema(GatewayContext context) {
        try {
            context.getSchemaUpdater().updatePersistentRecords(PersonRecord.META);
        }
        catch (SQLException e) {
            logger.error("Error verifying persistent records schemas for PersonRecord.", e);
        }
    }

    private void maybeCreatePersonRecord(GatewayContext context, long id, String firstname, String lastname, boolean isCool){
        logger.info("Attempting to create a person record.");
        try {
            PersonRecord newPerson = context.getLocalPersistenceInterface().createNew(PersonRecord.META);
            newPerson.setId(id);
            newPerson.setFirstname(firstname);
            newPerson.setLastname(lastname);
            newPerson.setIsCool(isCool);

            // ADD THE RECORD IF IT DOESN'T EXIST YET
            // IMPORTANT
            context.getSchemaUpdater().ensureRecordExists(newPerson);

        } catch (Exception e) {
            logger.error("Failed to create person record.", e);
        }
        logger.info("Person record created.");
    }


}
