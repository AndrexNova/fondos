#!/bin/bash

# Salir si hay algún error
set -e

# Ruta donde se encuentra la aplicación Spring Boot
APP_DIR="/home/ec2-user/server/fondos"
PID_FILE="$APP_DIR/application.pid"  # Archivo donde se guarda el PID del proceso

# Función para detener la aplicación
stop_application() {
    if [ -f "$PID_FILE" ]; then
        PID=$(cat "$PID_FILE")
        echo "Deteniendo la aplicación con PID $PID..."

        # Enviar señal de terminación al proceso
        kill $PID

        # Esperar a que el proceso se detenga
        sleep 5

        # Verificar si el proceso sigue en ejecución
        if ps -p $PID > /dev/null; then
            echo "La aplicación no se detuvo. Enviando señal de terminación forzada..."
            kill -9 $PID  # Terminar el proceso de forma forzada
        else
            echo "La aplicación se detuvo correctamente."
        fi

        # Eliminar el archivo PID
        rm -f "$PID_FILE"
    else
        echo "No se encontró el archivo PID. La aplicación puede no estar en ejecución."
    fi
}

# Llamar a la función para detener la aplicación
stop_application
