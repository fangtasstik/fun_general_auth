// enhanced types declarations for vue-router
import "vue-router";

declare module "vue-router" {
  interface RouteMeta extends AppRouteMeta {}
}