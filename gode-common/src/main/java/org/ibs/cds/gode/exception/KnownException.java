package org.ibs.cds.gode.exception;

public enum KnownException {

    FAILED_TO_START(1,"Failed to start app"){
        @Override
        public GodeStartupException exception(Throwable e,String details) {
            return details == null ? new GodeStartupException(e, new Error(getCode(), getMessage()))
                    : new GodeStartupException(getMessage(), e,new Error(getCode(), getMessage(), details));
        }

        public GodeStartupException exception(String details) {
            return details == null ? new GodeStartupException(new Error(getCode(), getMessage()))
                    : new GodeStartupException(getMessage(),new Error(getCode(), getMessage(), details));
        }
    },
    OBJECT_NOT_FOUND(2,"Specific object not found in system"){
        @Override
        public GodeRuntimeException exception(Throwable e,String details) {
            return details == null ? new GodeRuntimeException(e, new Error(getCode(), getMessage())) : new GodeRuntimeException(getMessage(), e,new Error(getCode(), getMessage(), details));
        }

        public GodeRuntimeException exception(String details) {
            return details == null ? new GodeRuntimeException(new Error(getCode(), getMessage())) : new GodeRuntimeException(getMessage(),new Error(getCode(), getMessage(), details));
        }
    };

    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    KnownException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public <T extends GodeException>  T exception(Throwable e){
        return exception(e, null);
    }
    public <T extends GodeException>  T exception(){
        return exception((String) null);
    }
    public abstract <T extends GodeException>  T exception(String details);
    public abstract <T extends GodeException>  T exception(Throwable e, String details);
}
