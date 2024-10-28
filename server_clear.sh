#!/bin/bash

# Salir si hay algún error
set -e

# Ruta donde se encuentra la aplicación
APP_DIR="/home/ec2-user/server/fondos"

# Detener el servicio de la aplicación si está en ejecución
echo "Deteniendo el servicio de la aplicación..."
if [ -f "$APP_DIR/application.pid" ]; then
    PID=$(cat "$APP_DIR/application.pid")
    kill -9 $PID || true
    echo "Servicio detenido."
else
    echo "No se encontró el PID del servicio."
fi

# Limpiar archivos temporales o de caché
echo "Limpiando archivos temporales..."
rm -rf "$APP_DIR/tmp/*"

# Limpiar logs antiguos (opcional)
echo "Limpiando logs antiguos..."
find "$APP_DIR/logs" -type f -name "*.log" -mtime +7 -exec rm -f {} \;

# Asegurarse de que el directorio esté vacío
echo "Asegurando que el directorio de la aplicación esté vacío..."
rm -rf "$APP_DIR/backup/*"

# Mensaje final
echo "Limpieza completada."
