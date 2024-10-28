#!/bin/bash

# Salir si hay algún error
set -e

# Ruta donde se encuentra la aplicación Spring Boot
APP_DIR="/home/ec2-user/server/fondos"
JAR_FILE="target/fondos-0.0.1-SNAPSHOT.jar"  # Reemplaza con el nombre de tu archivo JAR
PID_FILE="$APP_DIR/application.pid"  # Archivo para almacenar el PID del proceso

# Función para iniciar la aplicación
start_application() {
    echo "Iniciando la aplicación Spring Boot..."
    nohup java -jar "$APP_DIR/$JAR_FILE" > "$APP_DIR/logs/app.log" 2>&1 &
    echo $! > "$PID_FILE"  # Guarda el PID del proceso
    echo "Aplicación iniciada con PID $(cat $PID_FILE)."
}

# Verificar si la aplicación ya está en ejecución
if [ -f "$PID_FILE" ]; then
    PID=$(cat "$PID_FILE")
    if ps -p $PID > /dev/null; then
        echo "La aplicación ya está en ejecución con PID $PID."
        exit 0
    else
        echo "El archivo PID existe, pero no se encontró el proceso. Iniciando la aplicación..."
    fi
fi

# Iniciar la aplicación
start_application
