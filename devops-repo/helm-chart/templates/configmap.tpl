apiVersion: v1
kind: ConfigMap
metadata:
  name: {{ .Values.deploymentName | default .Values.repoName }}-configmap
data:
  {{if .Values.configuration}}
    {{if .Values.configuration.variables}}
      {{- range $index, $topping := .Values.configuration.variables }}
        {{ $index }}: {{ $topping | toString | replace "\"" "" | quote }}
      {{- end }}
    {{ end }}
    #this section checks if there are application.properties files to be mounted
    {{ if .Values.configuration.propertiesFiles}}
      {{- range $index, $topping := .Values.configuration.propertiesFiles.properties }}
        {{ $index }}: {{ $topping | toString | replace "\"" "" | quote }}
      {{- end }}
    {{ end }}
  {{ end }}
  {{ if .Values.external }}
    {{- range $index2, $topping2 := .Values.external.configuration.variables }}
      {{ $index2 }}: {{ $topping2 }}
    {{- end }}
  {{ end }}