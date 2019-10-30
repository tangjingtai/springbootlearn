package com.jt.springbootlearn.exception;

public class UserNotExistException extends RuntimeException {

    private Long id ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public UserNotExistException() {
        super("用户不存在");
    }

    public UserNotExistException(Long id){
        this();
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserNotExistException{" +
                "id=" + id +
                '}';
    }
}
