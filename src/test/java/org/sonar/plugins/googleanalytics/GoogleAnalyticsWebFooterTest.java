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

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.sonar.api.CoreProperties;

public class GoogleAnalyticsWebFooterTest {

  private Configuration conf;
  private GoogleAnalyticsWebFooter footer;

  @Before
  public void setUp() {
    conf = new BaseConfiguration();
    footer = new GoogleAnalyticsWebFooter(conf);
  }

  @Test
  public void shouldNotReturnFooterIfNotConfigured() {
    assertThat(footer.getHtml(), nullValue());
  }

  @Test
  public void shouldReturnHtml() {
    conf.setProperty(CoreProperties.GOOGLE_ANALYTICS_ACCOUNT_PROPERTY, "UA-1234567-8");
    String html = footer.getHtml();
    assertThat(html, containsString("google-analytics.com"));
    assertThat(html, containsString("UA-1234567-8"));
  }

}
