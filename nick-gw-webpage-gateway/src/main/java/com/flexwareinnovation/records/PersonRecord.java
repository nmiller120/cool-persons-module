package com.flexwareinnovation.records;


import com.inductiveautomation.ignition.gateway.localdb.persistence.BooleanField;
import com.inductiveautomation.ignition.gateway.localdb.persistence.IdentityField;
import com.inductiveautomation.ignition.gateway.localdb.persistence.PersistentRecord;
import com.inductiveautomation.ignition.gateway.localdb.persistence.RecordMeta;
import com.inductiveautomation.ignition.gateway.localdb.persistence.StringField;
import simpleorm.dataset.SFieldFlags;



public class PersonRecord extends PersistentRecord {
    public static final RecordMeta<PersonRecord> META = new RecordMeta<PersonRecord>(
            PersonRecord.class, "PersonRecord").setNounKey("PersonRecord.Noun").setNounPluralKey(
                    "PersonRecord.Noun.Plural");

    public static final IdentityField Id = new IdentityField(META, "Id");

    public static final StringField Firstname = new StringField(META, "Firstname", SFieldFlags.SMANDATORY, SFieldFlags.SDESCRIPTIVE);
    public static final StringField Lastname = new StringField(META, "Lastname", SFieldFlags.SMANDATORY, SFieldFlags.SDESCRIPTIVE);
    public static final BooleanField IsCool = new BooleanField(META, "IsCool", SFieldFlags.SMANDATORY, SFieldFlags.SDESCRIPTIVE);

    @Override
    public RecordMeta<?> getMeta() {
        return META;
    }

    @Override
    public String toString(){
        return getFirstname() + " " + getLastname();
    }

    public void setId(Long id) {
        setLong(Id, id);
    }

    public Long getId() {
        return getLong(Id);
    }

    public void setFirstname(String firstname) {
        setString(Firstname, firstname);
    }

    public String getFirstname(){return getString(Firstname);}

    public void setLastname(String lastname) {
        setString(Lastname, lastname);
    }

    public String getLastname(){return getString(Lastname);}

    public void setIsCool(boolean isCool){setBoolean(IsCool, isCool);}

    public boolean getIsCool(){return getBoolean(IsCool);}

    // Set field descriptions and properties
    static {

        Firstname.getFormMeta().setFieldNameKey("PersonRecord.Firstname.Name");
        Firstname.getFormMeta().setFieldDescriptionKey("PersonRecord.Firstname.Desc");
        Lastname.getFormMeta().setFieldNameKey("PersonRecord.Lastname.Name");
        Lastname.getFormMeta().setFieldDescriptionKey("PersonRecord.Lastname.Desc");
        IsCool.getFormMeta().setFieldNameKey("PersonRecord.IsCool.Name");
        IsCool.getFormMeta().setFieldDescriptionKey("PersonRecord.IsCool.Desc");

        // Set defaults
        Firstname.setDefault("John");
        Lastname.setDefault("Doe");
        IsCool.setDefault(false);

    }
}
