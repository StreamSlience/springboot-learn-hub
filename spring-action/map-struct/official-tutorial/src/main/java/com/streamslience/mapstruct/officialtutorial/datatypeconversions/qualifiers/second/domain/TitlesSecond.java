package com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.second.domain;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Named("TitleSecondTranslator")
public class TitlesSecond {

    @Named("EnglishToGerman")
    public String translateTitleEG(String title) {
        // some mapping logic
        return "EnglishToGerman";
    }

    @Named("GermanToEnglish")
    public String translateTitleGE(String title) {
        // some mapping logic
        return "GermanToEnglish";
    }

}
