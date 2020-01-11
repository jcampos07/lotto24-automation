package main.java.pageobjects.article;

import main.java.pageobjects.PageObjectBase;
import main.java.utilities.Log;
import org.openqa.selenium.By;

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
    private static final By TABLE_OF_CONTENTS_LIST = By.cssSelector("div #toc ul");


    public boolean isArticleTitleDisplayed() {
        Log.info("Attempt to verify if article title is displayed");
        return actionBot.getText(ARTICLE_TITLE).isEmpty();
    }

    public boolean isTableOfContentsDisplayed() {
        Log.info("Attempt to verify if table of contents is displayed");
        return actionBot.isElementDisplayed(TABLE_OF_CONTENTS_LIST);
    }
}
