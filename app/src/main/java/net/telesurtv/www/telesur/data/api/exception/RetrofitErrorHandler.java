package net.telesurtv.www.telesur.data.api.exception;


import net.telesurtv.www.telesur.data.api.exception.NetworkErrorException;
import net.telesurtv.www.telesur.data.api.exception.NetworkTimeOutException;
import net.telesurtv.www.telesur.data.api.exception.NetworkUknownHostException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by Jhordan on 28/07/15.
 */
public class RetrofitErrorHandler implements retrofit.ErrorHandler {

    @Override
    public Throwable handleError(retrofit.RetrofitError cause) {

        if (cause.getKind() == retrofit.RetrofitError.Kind.NETWORK) {

            if (cause.getCause() instanceof SocketTimeoutException)
                return new NetworkTimeOutException();

            else if (cause.getCause() instanceof UnknownHostException)
                return new NetworkUknownHostException();

            else if (cause.getCause() instanceof ConnectException)
                return cause.getCause();

        } else {

            return new NetworkErrorException();
        }

        return new Exception();
    }

    ;
}