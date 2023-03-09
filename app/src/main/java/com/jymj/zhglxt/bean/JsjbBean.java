package com.jymj.zhglxt.bean;

public class JsjbBean {

    private int id;
    private Long userId;
    private String text;
    private String label;
    private String color;
    private boolean isCheck=false;
    private boolean isDeleteShow=false;

    public JsjbBean() {
    }

    public JsjbBean(int id, String text, boolean isCheck) {
        this.id = id;
        this.text = text;
        this.isCheck = isCheck;
    }
    public JsjbBean(int id, String text, String color, boolean isCheck) {
        this.id = id;
        this.text = text;
        this.isCheck = isCheck;
        this.color = color;
    }
    public JsjbBean(int id, String text, boolean isCheck, boolean isDeleteShow) {
        this.id = id;
        this.text = text;
//        this.color = text;
        this.isCheck = isCheck;
        this.isDeleteShow = isDeleteShow;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLabel() {
        return label==null?"":label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColor() {
        if (color==null||color.equals("")){
            return "#4CA2FE";
        }
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        if (text==null||text.equals("")){
            return getLabel();
        }
        return text==null?"":text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isDeleteShow() {
        return isDeleteShow;
    }

    public void setDeleteShow(boolean deleteShow) {
        isDeleteShow = deleteShow;
    }
}
