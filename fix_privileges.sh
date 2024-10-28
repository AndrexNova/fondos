#!/bin/bash

# Salir si hay algún error
set -e

# Ruta donde se encuentra la aplicación Spring Boot
APP_DIR="/home/ec2-user/server/fondos"

# Usuario y grupo que deben poseer los archivos (usualmente 'ec2-user' o el que utilices)
USER="ec2-user"
GROUP="ec2-user"

# Cambiar los permisos del directorio de la aplicación
echo "Ajustando permisos en el directorio de la aplicación..."
chown -R $USER:$GROUP "$APP_DIR"
chmod -R 755 "$APP_DIR"

# Cambiar permisos de archivos específicos si es necesario
echo "Ajustando permisos en archivos específicos..."
chmod 644 "$APP_DIR/application.properties"  # Asegúrate de que este archivo sea legible

# Asegurarse de que el script tenga permisos de ejecución
chmod +x "$APP_DIR/server_clear.sh"  # Ajusta según la ruta de tu script

# Opcional: Configurar el archivo de log (si es necesario)
LOG_FILE="$APP_DIR/logs/fix_privileges.log"
{
    echo "Fecha y hora: $(date)"
    echo "Permisos ajustados para: $APP_DIR"
    echo "Propietario establecido en: $USER:$GROUP"
} >> "$LOG_FILE"

# Mensaje final
echo "Los permisos han sido ajustados correctamente."
