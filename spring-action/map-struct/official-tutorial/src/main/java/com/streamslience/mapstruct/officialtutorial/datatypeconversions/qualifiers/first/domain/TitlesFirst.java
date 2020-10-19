package com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.first.domain;

import com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.first.EnglishToGerman;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.first.GermanToEnglish;
import com.streamslience.mapstruct.officialtutorial.datatypeconversions.qualifiers.first.TitleFirstTranslator;
import org.springframework.stereotype.Component;

@Component
@TitleFirstTranslator
public class TitlesFirst {

    @EnglishToGerman
    public String translateTitleEG(String title) {
        // some mapping logic
        return "EnglishToGerman";
    }

    @GermanToEnglish
    public String translateTitleGE(String title) {
        // some mapping logic
        return "GermanToEnglish";
    }

}
