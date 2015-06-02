package com.firstutility.taf.core.ui.controls.devices;

public enum Keys {

    ENTER('\n'),
    BACKSPACE('\b'),
    TAB('\t'),
    CANCEL(0x03),
    CLEAR(0x0C),
    SHIFT(0x10),
    CONTROL(0x11),
    ALT(0x12),
    PAUSE(0x13),
    CAPSLOCK(0x14),
    ESCAPE(0x1B),
    SPACE(0x20),
    PAGE_UP(0x21),
    PAGE_DOWN(0x22),
    END(0x23),
    HOME(0x24),

    LEFT(0x25),
    UP(0x26),
    RIGHT(0x27),
    DOWN(0x28),


    NUMBER_0(0x30),
    NUMBER_1(0x31),
    NUMBER_2(0x32),
    NUMBER_3(0x33),
    NUMBER_4(0x34),
    NUMBER_5(0x35),
    NUMBER_6(0x36),
    NUMBER_7(0x37),
    NUMBER_8(0x38),
    NUMBER_9(0x39),

    A(0x41),
    B(0x42),
    C(0x43),
    D(0x44),
    E(0x45),
    F(0x46),
    G(0x47),
    H(0x48),
    I(0x49),
    J(0x4A),
    K(0x4B),
    L(0x4C),
    M(0x4D),
    N(0x4E),
    O(0x4F),
    P(0x50),
    Q(0x51),
    R(0x52),
    S(0x53),
    T(0x54),
    U(0x55),
    V(0x56),
    W(0x57),
    X(0x58),
    Y(0x59),
    Z(0x5A);


    private int keyCode;

    private Keys(int inputEvent) {
        this.keyCode = inputEvent;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
