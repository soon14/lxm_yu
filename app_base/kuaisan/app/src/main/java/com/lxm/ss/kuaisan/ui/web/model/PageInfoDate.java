package com.lxm.ss.kuaisan.ui.web.model;

/**
 * Created by john on 2016/5/25.
 */
public class PageInfoDate {
    private String title;
    private boolean show_header_navibar;
    private boolean show_footer_navibar;
    public boolean isShow_header_navibar() {
        return show_header_navibar;
    }

    public void setShow_header_navibar(boolean show_header_navibar) {
        this.show_header_navibar = show_header_navibar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isShow_footer_navibar() {
        return show_footer_navibar;
    }

    public void setShow_footer_navibar(boolean show_footer_navibar) {
        this.show_footer_navibar = show_footer_navibar;
    }

    @Override
    public String toString() {
        return "PageInfoDate{" +
                "title='" + title + '\'' +
                ", show_header_navibar=" + show_header_navibar +
                ", show_footer_navibar=" + show_footer_navibar +
                '}';
    }
}
