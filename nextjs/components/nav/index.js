import React from 'react';
import Link from 'next/link';
import { useRouter } from 'next/router';
import styles from './Nav.module.scss';

export function Nav() {
  const { pathname } = useRouter();
  const isHomePage = pathname === "/"
  const homeLink = !isHomePage

  return (
    <nav className={styles.nav}>
      <div>
        <span id="hello">Hello, World!</span>
      </div>
      { homeLink && (
        <div className={styles.homeLinkWrapper}>
          <Link href="/" className={styles.homeLink}> ← Home </Link>
        </div>)}
    </nav>
  );
}