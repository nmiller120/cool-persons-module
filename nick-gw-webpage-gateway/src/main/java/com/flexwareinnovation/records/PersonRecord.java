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

    public static final IdentityField Id = new IdentityField(META);

    public static final StringField Firstname = new StringField(META, "Firstname", SFieldFlags.SMANDATORY);
    public static final StringField Lastname = new StringField(META, "Lastname", SFieldFlags.SMANDATORY);
    public static final BooleanField IsCool = new BooleanField(META, "IsCool", SFieldFlags.SMANDATORY);

    @Override
    public RecordMeta<?> getMeta() {
        return META;
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
}
