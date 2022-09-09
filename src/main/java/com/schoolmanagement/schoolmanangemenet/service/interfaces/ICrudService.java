package com.schoolmanagement.schoolmanangemenet.service.interfaces;

import java.util.List;

public interface ICrudService<T> {
    Boolean create (T object);

    List<T> read ();

    Boolean update (T object);

    Boolean delete (T object);
}
