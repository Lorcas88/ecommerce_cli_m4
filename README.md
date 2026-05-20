# Módulo 4 – E-commerce CLI

Aplicación de consola en Java que simula un sistema de E-commerce con dos flujos principales: Administración (gestión de catálogo de servicios) y Usuario (gestión de carrito de cotización, aplicación de descuentos automáticos y creación de órdenes).

## Enlace al Repositorio (GitHub)

**[Haz clic aquí para ver el repositorio público en GitHub](https://github.com/Lorcas88/ecommerce_cli_m4)**

---

## Instrucciones de Ejecución

### Requisitos Previos

- **Java JDK:** Versión 17 o superior.
- **Maven:** Instalado y configurado en el PATH del sistema.

### Pasos para ejecutar:

1. Clona este repositorio o descomprime el archivo `.zip`.
2. Abre una terminal en la ruta raíz del proyecto (donde se encuentra el archivo `pom.xml`).
3. Compila el proyecto y ejecuta las pruebas unitarias:
   ```bash
   mvn clean test
   ```
4. Ejecuta la aplicación:
   ```bash
   mvn exec:java -Dexec.mainClass="xyz.lorcasdev.Main"
   ```
   _Alternativamente, puedes abrir el proyecto en tu IDE favorito (IntelliJ IDEA, Eclipse, VS Code) y ejecutar la clase `Main.java` directamente._

---

## Estructura de Menús

### Menú Principal

Al iniciar la aplicación, se cargarán datos de prueba (Seeder) y verás:

1. **Menú de Administración**
2. **Menú de Usuario (Tienda)**
3. **Salir**

### Menú de Administración (Gestión de Productos/Servicios)

Permite gestionar el catálogo mediante operaciones CRUD:

- `1` Listar servicios (Catálogos)
- `2` Buscar servicio por ID
- `3` Crear nuevo servicio
- `4` Editar servicio
- `5` Eliminar (desactivar) servicio

### Menú de Usuario (Carrito y Compra)

Permite interactuar con la tienda y generar compras:

- `1` Explorar catálogo de servicios disponibles
- `2` Agregar servicio a la cotización
- `3` Ver mi cotización actual (ítems, subtotales y horas)
- `4` Eliminar servicio de la cotización
- `5` Ver descuentos activos (muestra las reglas vigentes)
- `6` Confirmar cotización (Crear Orden y aplicar descuentos)

---

## Ejemplo Breve de Compra

A continuación, se muestra un flujo de compra típico utilizando los datos cargados en memoria:

1. Entrar al **Menú de Usuario** (Opción `2` en el Menú Principal).
2. Seleccionar `5` para **Ver descuentos activos**:
   ```text
   --- Descuentos Activos ---
   1. 10% de descuento en compras a partir de $1,000,000
   2. 5% de descuento en compras a partir de $500,000
   ```
3. Seleccionar `1` para **Explorar el catálogo** y anotar un ID (por ejemplo, el ID `2` que corresponde a Desarrollo Backend "Dashboard Admin" por $1,800,000).
4. Seleccionar `2` para **Agregar servicio a la cotización**:
   - _Nombre:_ "Juan Pérez"
   - _ID del servicio:_ `2`
   - _Cantidad:_ `1`
5. Seleccionar `3` para **Ver la cotización actual** (El subtotal será de $1,800,000).
6. Seleccionar `6` para **Confirmar cotización (Crear Orden)**. El sistema calculará y aplicará automáticamente el descuento que corresponda (en este caso, 10% por superar el millón):

   ```text
   Confirmando su cotización y generando la orden de compra...

   *** DESCUENTO APLICADO ***
   Regla: 10% de descuento por compras a partir de $1,000,000
   Descuento: -$110000
   ¡Orden generada con éxito!
   Order[1] - Customer: Juan Perez | Status: En progreso | Total: $990000
   ```

---

## 🧪 Pruebas Unitarias (JUnit)

El proyecto incluye validaciones probadas mediante JUnit 5, cubriendo:

- Cálculos correctos de totales y subtotales en el carrito (`QuoteServiceTest`).
- Validación de cantidad mayor a 0 al actualizar ítems (`QuoteServiceTest`).
- Aplicación automática y correcta de reglas matemáticas de descuento (`DiscountServiceTest`).
