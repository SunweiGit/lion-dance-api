package cn.liondance.liondanceapi.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * The type Rest client config.
 *
 * @author 孙炜
 */
@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.elasticsearch")
public class ElasticsearchClientConfig {

  private String[] uris;
  private String username;
  private String password;

  /**
   * Elasticsearch client rest high level client.
   *
   * @return the rest high level client
   * @throws KeyStoreException the key store exception
   * @throws NoSuchAlgorithmException the no such algorithm exception
   * @throws KeyManagementException the key management exception
   * @throws URISyntaxException the uri syntax exception
   */
  @Bean
  public ElasticsearchClient elasticsearchClient() {
    log.error("loading elasticsearch info uris{} username{} password{}", uris, username, password);
    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(
        AuthScope.ANY, new UsernamePasswordCredentials(username, password));
    HttpHost[] httpHost = new HttpHost[uris.length];
    for (int i = 0; i < uris.length; i++) {
      URI uri = URI.create(uris[i]);
      httpHost[i] = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
    }
    RestClientBuilder restClientBuilder =
        RestClient.builder(httpHost)
            .setHttpClientConfigCallback(
                httpClientBuilder -> {
                  httpClientBuilder.disableAuthCaching();
                  return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                });
    ElasticsearchTransport transport =
        new RestClientTransport(restClientBuilder.build(), new JacksonJsonpMapper());
    return new ElasticsearchClient(transport);
  }
}
