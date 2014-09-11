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

import org.apache.commons.lang.StringUtils;
import org.sonar.api.CoreProperties;
import org.sonar.api.config.Settings;
import org.sonar.api.web.Footer;

public class GoogleAnalyticsWebFooter implements Footer {

  private Settings settings;

  public GoogleAnalyticsWebFooter(Settings settings) {
    this.settings = settings;
  }

  protected String getIdAccount() {
    return settings.getString(CoreProperties.GOOGLE_ANALYTICS_ACCOUNT_PROPERTY);
  }

  public String getKey() {
    return "webfooter_" + CoreProperties.GOOGLE_ANALYTICS_PLUGIN;
  }

  public String getHtml() {
    String id = getIdAccount();
    if (StringUtils.isNotBlank(id)) {
      return "<script type=\"text/javascript\">\n" +
        "  var _gaq = _gaq || [];\n" +
        "  _gaq.push(['_setAccount', '" + id + "']);\n" +
        "  _gaq.push(['_trackPageview']);\n" +
        "  (function() {\n" +
        "    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;\n" +
        "    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';\n" +
        "    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);\n" +
        "  })();\n" +
        "</script>";
    }
    return null;
  }

  @Override
  public String toString() {
    return getKey();
  }

}
