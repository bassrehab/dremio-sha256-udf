This is a SHA256 UDF for Dremio.
Uses Arrow Buffer standard byte stream read and Guava library for SHA256 calculation.
Assumes; dremio-sabot-kernel is available in path. 

Usage:
```
SELECT sha256_udf(column_name) FROM some_table_vds
```
As of Dremio release version v4.8.x no SHA2 - 256 implementation was available.
