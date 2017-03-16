package com.culqi.util;

/**
 * Created by culqi on 15/03/17.
 */
public enum Reason {

    duplicado {
        public String toString() {
            return "duplicado";
        }
    },

    fraudulento {
        public String toString() {
            return "fraudulento";
        }
    },

    solicitud_comprador {
      public String toString(){
          return "solicitud_comprador";
      }
    }

}
