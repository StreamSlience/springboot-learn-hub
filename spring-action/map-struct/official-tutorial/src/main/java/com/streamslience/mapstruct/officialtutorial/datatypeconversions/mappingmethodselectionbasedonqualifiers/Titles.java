package com.streamslience.mapstruct.officialtutorial.datatypeconversions.mappingmethodselectionbasedonqualifiers;

import org.mapstruct.Named;

@TitleTranslator
//@Named("TitleTranslator")
public class Titles {

    @EnglishToGerman
    //@Named("EnglishToGerman")
    public String translateTitleEG(String title) {
        // some mapping logic
        return "EnglishToGerman";
    }

    @GermanToEnglish
    //@Named("GermanToEnglish")
    public String translateTitleGE(String title) {
        // some mapping logic
        return "GermanToEnglish                                                                                                                 ";
    }

}
