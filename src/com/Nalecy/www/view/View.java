package com.Nalecy.www.view;

public abstract class View {
    protected boolean hasInit = false;
    /**
     * 展示
     */
    public abstract void display();

    /**
     * 关闭窗口（释放数据）
     */
    public abstract void close();

    /**
     * 隐藏窗口（保留数据）
     */
    public abstract void hide();
}
