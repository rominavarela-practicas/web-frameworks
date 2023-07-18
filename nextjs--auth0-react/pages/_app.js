import Router from 'next/router';
import { Auth0Provider } from '@auth0/auth0-react';
import '../styles/globals.scss';

const onRedirectCallback = (appState) => {
  Router.replace(appState?.returnTo || '/');
};

export default function App({ Component, pageProps }) {
  return (
    <Auth0Provider
      domain={process.env.NEXT_PUBLIC_DOMAIN}
      clientId={process.env.NEXT_PUBLIC_CLIENT_ID}
      onRedirectCallback={onRedirectCallback}
      authorizationParams={{
        scope: process.env.NEXT_PUBLIC_SCOPE,
        audience: process.env.NEXT_PUBLIC_AUDIENCE,
        redirect_uri: typeof window !== 'undefined' && window.location.origin,
      }}
    >
      <Component {...pageProps} />
    </Auth0Provider>
  );
}