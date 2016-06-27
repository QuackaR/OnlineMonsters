package de.krien.games.OnlineMonsters.model.util.vector;

public class Vector2i {

    public int x;
    public int y;

    public Vector2i() {
    }

    public Vector2i(int x, int y) {
        this.set(x, y);
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("Vector3i[");
        sb.append(this.x);
        sb.append(", ");
        sb.append(this.y);
        sb.append(']');
        return sb.toString();
    }

    public final int getX() {
        return this.x;
    }

    public final int getY() {
        return this.y;
    }

    public final void setX(int x) {
        this.x = x;
    }

    public final void setY(int y) {
        this.y = y;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj == null) {
            return false;
        } else if(this.getClass() != obj.getClass()) {
            return false;
        } else {
            Vector2i other = (Vector2i)obj;
            return this.x == other.x && this.y == other.y;
        }
    }

}
