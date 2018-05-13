apiVersion: v1
kind: Service
metadata:
  name: {{ .Values.serviceName | default .Values.repoName }}-service
spec:
  selector:
    run: {{ .Values.deploymentName | default .Values.repoName }}-deployment
  ports:
  - protocol: TCP
    port: {{ .Values.service.externalPort }}
    targetPort: {{ .Values.service.internalPort }}
  {{$service_type := typeOf .Values.service.type}}
  {{$string_type := typeOf "string"}}
  {{ if eq $service_type $string_type }}
  type: {{ .Values.service.type }}
  {{ end }}
