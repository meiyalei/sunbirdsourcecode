//$Id: RSSImage.java,v 1.5 2004/03/25 10:09:10 taganaka Exp $
package cn.itcast.rss.rsslib4j;

/**
 * RSS image's definitions class.
 *
 * <blockquote>
 * <em>This module, both source code and documentation, is in the
 * Public Domain, and comes with <strong>NO WARRANTY</strong>.</em>
 * </blockquote>
 *
 * @since RSSLIB4J 0.1
 * @author Francesco aka 'Stealthp' stealthp[@]stealthp.org
 * @version 0.2
 */


public class RSSImage extends RSSObject{

  /**
 * @uml.property  name="url"
 */
private String url;
  /**
 * @uml.property  name="w"
 */
private String w;
  /**
 * @uml.property  name="h"
 */
private String h;

  /**
 * Set url  of the image
 * @param u  The image's url
 * @uml.property  name="url"
 */
  public void setUrl(String u) {
    this.url = u;
  }

  /**
 * Get the url of the image
 * @return  the image's url
 * @uml.property  name="url"
 */
  public String getUrl() {
    return this.url;
  }

  /**
   * Set the image's width
   * @param width width
   */
  public void setWidth(String width){
    w = width;
  }

  /**
   * Set the image's height
   * @param height height
   */
  public void setHeight(String height){
    h = height;
  }

  /**
   * Get the image's width
   * @return width (could be null)
   */
  public String getWidth(){
    return w;
  }

  /**
   * Get the image's height
   * @return height (could be null)
   */
  public String getHeight(){
    return h;
  }

  /**
   * Return a html img tag with link associated
   * @return html
   */
  public String toHTML(){
    String html = "<a href=\""+link+"\">";
    html += "<img src=\""+url+"\" border=\"0\" ";
    html += (w != null) ? "width=\""+w+" \""  : " ";
    html += (h != null) ? "height=\""+h+" \"" : " ";
    html += (title != null) ? "alt=\""+title+"\"" : "";
    html += "/>";
    html += "</a>";
    return html;
  }

  /**
   * Useful for debug
   * @return information
   */
  public String toString() {
    String info = "TITLE: " + title + "\n" + "LINK: " + link + "\n" + "URL: " +
        url;
    return info;
  }


}