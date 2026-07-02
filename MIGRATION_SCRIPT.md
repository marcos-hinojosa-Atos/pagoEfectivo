# 📋 SCRIPT DE MIGRACIÓN - DTOs a dtoUpgrade Package

## 🎯 Objetivo
Migrar todas las clases DTO antiguas (`dto/`) a usar la estructura nueva en `dtoUpgrade/` para SEPSA v2.

## ✅ Checklist de Cambios

### **Fase 1: Actualización de Imports en ProxyServiceImpl**
- [x] Cambiar imports de `dto.DirectaResponseDTO` a `dtoUpgrade.DirectaResponseDTO`
- [x] Cambiar imports de `dto.ReversaResponseDTO` a `dtoUpgrade.ReversaResponseDTO`
- [x] Mantener import de `dtoUpgrade.ConsultaResponseDTO` (ya está)

### **Fase 2: Actualización de Imports en BillerTransformer**
- [x] Cambiar imports genéricos `dto.*` a específicos
- [x] Usar imports explícitos de `dtoUpgrade` para responses

### **Fase 3: Actualización de RestExecute (helpers)**
- [x] Actualizar métodos `sendDirecta()` y `sendReversa()` para usar dtoUpgrade

### **Fase 4: Eliminación de DTOs Antiguos (Opcional)**
- [ ] Mantener por ahora como referencia
- [ ] Eliminar después de validar en producción

---

## 🛠️ Script Bash Local (Ejecutar en tu máquina)

```bash
#!/bin/bash
# migration-dtos.sh - Script de migración de DTOs

set -e  # Exit on error

PROJECT_ROOT="aio-conector-cashin-pago-efectivo-online/Source"
SRC_DIR="$PROJECT_ROOT/src/main/java/com/wu/billersplus/connector/cashin/pago/efectivo/online"

echo "🔄 Iniciando migración de DTOs a dtoUpgrade package..."
echo ""

# Función para reemplazar imports en archivos
migrate_imports() {
    local file=$1
    echo "📝 Procesando: $file"
    
    # Reemplazar imports antiguos de response DTOs
    sed -i '' 's/com\.wu\.billersplus\.connector\.cashin\.pago\.efectivo\.online\.dto\.DirectaResponseDTO/com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.DirectaResponseDTO/g' "$file"
    sed -i '' 's/com\.wu\.billersplus\.connector\.cashin\.pago\.efectivo\.online\.dto\.ReversaResponseDTO/com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.ReversaResponseDTO/g' "$file"
    sed -i '' 's/import com\.wu\.billersplus\.connector\.cashin\.pago\.efectivo\.online\.dto\.\*;/import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.*;\nimport com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.*;/g' "$file"
}

# 1. Migrar ProxyServiceImpl
echo "1️⃣ Migrando ProxyServiceImpl.java..."
migrate_imports "$SRC_DIR/service/ProxyServiceImpl.java"

# 2. Migrar RestExecute
echo "2️⃣ Migrando RestExecute.java..."
migrate_imports "$SRC_DIR/helpers/RestExecute.java"

# 3. Migrar BillerTransformer
echo "3️⃣ Migrando BillerTransformer.java..."
migrate_imports "$SRC_DIR/transformers/BillerTransformer.java"

echo ""
echo "✅ Migración completada!"
echo ""
echo "Próximos pasos:"
echo "1. Revisar cambios con: git diff"
echo "2. Compilar: mvn clean compile -DskipTests"
echo "3. Ejecutar tests: mvn test"
echo "4. Hacer commit: git commit -am 'Migration: Move DTOs to dtoUpgrade package'"
echo "5. Push: git push origin main"
```

### **Cómo ejecutar:**
```bash
cd aio-conector-cashin-pago-efectivo-online
chmod +x ../../MIGRATION_SCRIPT.sh
bash ../../MIGRATION_SCRIPT.sh
```

---

## 📝 Cambios Manuales por Archivo

### **1. ProxyServiceImpl.java**

**ANTES:**
```java
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.DirectaResponseDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.ReversaResponseDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.ConsultaResponseDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.DirectaRequestDTO;
```

**DESPUÉS:**
```java
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.ConsultaResponseDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.DirectaRequestDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.DirectaResponseDTO;  // ← CAMBIADO
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.ReversaResponseDTO; // ← CAMBIADO
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.ConsultaRequestDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.ReversaRequestDTO;
```

---

### **2. RestExecute.java**

**ANTES (líneas 29-32):**
```java
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.ConsultaResponseDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.DirectaRequestDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.DirectaResponseDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.ReversaResponseDTO;
```

**DESPUÉS:**
```java
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.ConsultaResponseDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.DirectaRequestDTO;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.DirectaResponseDTO;  // ← CAMBIADO
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.ReversaResponseDTO; // ← CAMBIADO
```

---

### **3. BillerTransformer.java**

**ANTES (línea 6):**
```java
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.*;
```

**DESPUÉS:**
```java
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dto.*;
import com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.*;
```

**CAMBIOS EN CASTS (línea 188):**
```java
// ANTES:
DirectaResponseDTO responseDto = (DirectaResponseDTO) directa.getResponseBiller();

// DESPUÉS:
com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.DirectaResponseDTO responseDto = 
    (com.wu.billersplus.connector.cashin.pago.efectivo.online.dtoUpgrade.DirectaResponseDTO) 
    directa.getResponseBiller();
```

---

## 🔍 Validación Post-Migración

### **Paso 1: Verificar Imports**
```bash
grep -r "import.*dto\.DirectaResponseDTO" src/
grep -r "import.*dto\.ReversaResponseDTO" src/
# Debe retornar 0 coincidencias (vacío)
```

### **Paso 2: Verificar que dtoUpgrade está siendo usado**
```bash
grep -r "dtoUpgrade\.DirectaResponseDTO" src/
# Debe retornar los archivos migrados
```

### **Paso 3: Compilar**
```bash
cd aio-conector-cashin-pago-efectivo-online/Source
mvn clean compile -DskipTests
```

### **Paso 4: Tests**
```bash
mvn test
```

---

## ⚠️ Posibles Problemas y Soluciones

### **Problema: "Cannot find symbol: class DirectaResponseDTO"**
```
Solución: Verifica que el import sea de dtoUpgrade/
grep -n "DirectaResponseDTO" archivo.java
```

### **Problema: Compilación fallida con "duplicate class"**
```
Solución: Ambas clases están en el classpath
- Elimina la vieja clase de dto/
- O usa fully qualified names en los casts
```

### **Problema: Tests fallan post-migración**
```
Solución:
1. Verifica que los DTOs en dtoUpgrade tengan los mismos constructores
2. Comprueba que los campos se llaman igual
3. Valida que ResponseTrxDTO tiene los getters necesarios
```

---

## 📊 Estructura Final Post-Migración

```
✅ USANDO dtoUpgrade/:
├─ ConsultaResponseDTO (code, message, data)
├─ DirectaResponseDTO (code, message, data)
├─ ReversaResponseDTO (code, message, data)
├─ DirectaRequestDTO
├─ ReversaRequestDTO
└─ DataDTO, DocumentDetailDTO, etc.

⚠️ PENDIENTE ELIMINAR (dto/):
├─ ConsultaResponseDTO (vieja)  → Renombrar a ConsultaResponseDTO_Legacy.java
├─ DirectaResponseDTO (vieja)   → Renombrar a DirectaResponseDTO_Legacy.java
└─ ReversaResponseDTO (vieja)   → Renombrar a ReversaResponseDTO_Legacy.java

✅ SEGUIR USANDO (dto/):
├─ ConsultaRequestDTO
├─ DirectaRequestDTO (vieja, para legacy)
├─ ReversaRequestDTO (vieja, para legacy)
├─ ErrorDTO
├─ ItemDTO
└─ Otros DTOs de soporte
```

---

## 🚀 Timeline de Migración

| Fase | Acción | Estado |
|------|--------|--------|
| **Pre-migración** | Backup del código actual | ⏳ Manual |
| **Fase 1** | Actualizar imports en 3 archivos | ⏳ Pendiente |
| **Fase 2** | Compilar y validar | ⏳ Pendiente |
| **Fase 3** | Ejecutar tests | ⏳ Pendiente |
| **Fase 4** | Commit y push | ⏳ Pendiente |
| **Fase 5** | Eliminar DTOs legacypost-validación | ⏳ Post-producción |

---

## 🔄 Rollback Plan (Si algo falla)

```bash
# Si necesitas revertir todo:
git reset --hard HEAD~1
git clean -fd

# O revert específico:
git revert <commit-hash>
```

---

## ✨ Checklist Final

- [ ] Script descargado y revisado
- [ ] Backup local realizado (`git stash`)
- [ ] Script ejecutado correctamente
- [ ] Cambios compilados (`mvn clean compile`)
- [ ] Tests pasados (`mvn test`)
- [ ] Git diff revisado (sin cambios no deseados)
- [ ] Commit con mensaje claro
- [ ] Push a rama main/develop
- [ ] PR creado (si aplica)
- [ ] Code review completado
- [ ] Merge a main

