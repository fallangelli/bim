package com.family.grab.model;

import com.family.grab.model.annotation.HelpUrl;
import com.family.grab.model.annotation.TargetUrl;

/**
 * @author code4crafer@gmail.com
 */
@TargetUrl(value = "http://grab.io/post/\\d+", sourceRegion = "//li[@class='post']")
@HelpUrl(value = "http://grab.io/list/\\d+", sourceRegion = "//li[@class='list']")
public class MockModel {

}
