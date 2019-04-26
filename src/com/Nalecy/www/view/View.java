package com.Nalecy.www.view;

public abstract class View {
    /**
     * 是否已初始化 false为否
     */
    protected boolean hasInit = false;
    /**
     * 判断是否已初始化，若已初始化直接展示，若未初始化先初始化再展示
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
