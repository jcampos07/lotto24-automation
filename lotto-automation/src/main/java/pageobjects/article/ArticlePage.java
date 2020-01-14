package main.java.pageobjects.article;

import main.java.pageobjects.PageObjectBase;
import main.java.utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Article page that contains the functions this page object offers
 *
 * @author Jason Campos on 1/11/2020
 */

public class ArticlePage extends PageObjectBase {

    /**
     * Web elements
     */
    private static final By ARTICLE_TITLE = By.id("firstHeading");
    private static final By TABLE_OF_CONTENTS = By.cssSelector("div#toc");
    private static final By TABLE_OF_CONTENTS_LIST = By.cssSelector("a span.toctext");
    private static final By SECTION_LIST = By.className("mw-headline");


    public boolean isArticleTitleDisplayed() {
        Log.info("Attempt to verify if article title is displayed");
        return actionBot.getText(ARTICLE_TITLE).isEmpty();
    }

    public boolean isTableOfContentsDisplayed() {
        Log.info("Attempt to verify if table of contents is displayed");
        return actionBot.isElementDisplayed(TABLE_OF_CONTENTS);
    }

    /**
     * Get the elements text for either the table of contents or the sections titles, we use a lambda expresion
     * in order to get the text of the elements
     * @param section String with the Web elements to get, it could be the table of content or sections list
     * @return List<String></String> with the text of the elements
     */
    public List<String> getElementsText(String section) {
        Log.info(String.format("Attempt to get Elements text for %s", section));
        By element;
        if (section.equalsIgnoreCase("content table")) {
            element = TABLE_OF_CONTENTS_LIST;
        } else {
            element = SECTION_LIST;
        }
        return actionBot.findAll(element).stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
