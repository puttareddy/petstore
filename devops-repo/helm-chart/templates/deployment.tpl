{{- $repoName := .Values.repoName -}}
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: {{ .Values.deploymentName | default .Values.repoName }}-deployment
spec:
  replicas: 1
  template:
    metadata:
      name: {{ .Values.deploymentName | default .Values.repoName }}-deployment
      labels:
        run: {{ .Values.deploymentName | default .Values.repoName | toString }}-deployment
    spec:
      containers:
        - name: {{ .Values.deploymentName | default .Values.repoName  }}-deployment
          image: {{.Values.imageName }}      
          {{ if .Values.image.pullPolicy }}
          imagePullPolicy: {{ .Values.image.pullPolicy }}
          {{ else }}
          imagePullPolicy: Always
          {{ end }}
          # logging paths
          volumeMounts:
          {{if .Values.configuration}}
            {{if .Values.configuration.pvcs}}
            - mountPath: {{ .Values.configuration.pvcs.mountPath }}
              name: {{ .Values.configuration.pvcs.pvcName | default .Values.repoName }}
            {{end}}
            #this section checks if there are application.properties and creates a volume map for that config file
            {{if .Values.configuration.propertiesFiles}}
            - mountPath: {{ .Values.configuration.propertiesFiles.configPath }}
              name: config-volume
            {{ end }}
          {{if .Values.configuration.variables}}
          envFrom:
          - configMapRef:
              name: {{ .Values.deploymentName | default .Values.repoName }}-configmap
            {{ end }}
            {{if .Values.configuration.secrets}}
          env:
            {{- range $index, $secret_list := .Values.configuration.secrets }}
              {{- range $index2, $secrets := $secret_list }}
                {{- range $secret := $secrets }}
                  - name: {{$index2 | replace "-" "_"}}_{{$secret | replace "." "_" }}
                    valueFrom:
                      secretKeyRef:
                        name: {{$index2}}
                        key: {{$secret}}
                {{- end }}
              {{- end }}
            {{- end }}
            {{ end }}
          {{ end }}
          lifecycle:
            postStart:
              exec:
                command:
                  - "sh"
                  - "-c"
                  - "echo 142.241.8.151 petstore.web.info >> /etc/hosts; "
          ports:
            - containerPort: {{ .Values.service.internalPort  | int }}
          {{if .Values.resources}}
          resources:
{{ toYaml .Values.resources | indent 12 }}
          {{end}}
         
      volumes:
      {{if .Values.configuration}}
        {{if .Values.configuration.pvcs}}
        - name: {{ .Values.configuration.pvcs.pvcName | default .Values.repoName }}
          persistentVolumeClaim:
            claimName: {{ .Values.configuration.pvcs.pvcName | default .Values.repoName }}
        {{ end }}
        #this section checks if there are application.properties and creates a volume for that config file
        {{if .Values.configuration.propertiesFiles}}
        - name: config-volume
          configMap:
            name: {{ .Values.deploymentName | default .Values.repoName  }}-configmap
        {{ end }}
      {{ end }}
