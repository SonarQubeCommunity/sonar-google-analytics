/*
 * Sonar Google Analytics Plugin
 * Copyright (C) 2009 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.plugins.googleanalytics;

import org.junit.Before;
import org.junit.Test;
import org.sonar.api.CoreProperties;
import org.sonar.api.config.PropertyDefinitions;
import org.sonar.api.config.Settings;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class GoogleAnalyticsWebFooterTest {

  private Settings settings;
  private GoogleAnalyticsWebFooter footer;

  @Before
  public void setUp() {
    settings = new Settings(new PropertyDefinitions(GoogleAnalyticsPlugin.class));
    footer = new GoogleAnalyticsWebFooter(settings);
  }

  @Test
  public void test() {
    assertThat(footer.getKey(), notNullValue());
    assertThat(footer.toString(), is(footer.getKey()));
  }

  @Test
  public void shouldNotReturnFooterIfNotConfigured() {
    assertThat(footer.getHtml(), nullValue());
  }

  @Test
  public void shouldReturnHtml() {
    settings.setProperty(CoreProperties.GOOGLE_ANALYTICS_ACCOUNT_PROPERTY, "UA-1234567-8");
    String html = footer.getHtml();
    assertThat(html, containsString("google-analytics.com"));
    assertThat(html, containsString("UA-1234567-8"));
  }

}
