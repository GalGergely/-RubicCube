class IdDisplay {
    int id;

    public IdDisplay(int id) {
        this.id = id;
    }

    void show() {
        fill(0);
        scale(0.02);
        text(id, 0 , 0 , 1 );
        scale(1, -1);
        text(id, 0 , 0 , -1 );
        scale(1, -1);
    }
}
