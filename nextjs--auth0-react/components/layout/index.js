import styles from './Layout.module.scss';
import { Nav } from '@/components/nav';
import Footer from '@/components/footer';

export default function Layout({ children }) {
  return (
    <div className={styles.layout}>
      <Nav />
      <div className={styles.layoutContainer}>
        {children}
      </div>
      <Footer/>
    </div>
  );
}